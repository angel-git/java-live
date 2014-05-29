package com.ags.jonlive.v1;

import com.ags.jonlive.exception.JOnLiveException;
import com.ags.jonlive.service.Executor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Angel
 * @since 03/05/2014
 */
public class CodeExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(CodeExecutor.class);


    public void load(String importCode, String codeToExecute, HttpServletRequest request) throws JOnLiveException {
        writeJavaClass(importCode, codeToExecute);
        URLClassLoader classLoader = compileJavaClass();

        try {
            Class<?> cls = Class.forName("com.ags.jonlive.v1.SpringExecutor", true, classLoader);
            Object instance = cls.getConstructor(ServletContext.class).newInstance(request.getSession().getServletContext());
            if (instance instanceof Executor) {
                ((Executor) instance).executeCode();
            }
        } catch (Exception e) {
            LOG.error("can't create the instance of the class", e);
        }
    }

    private URLClassLoader compileJavaClass() throws JOnLiveException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        URL resource = getClass().getResource("SpringExecutor.java");
        URLClassLoader classLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();

        int compilationResult = compiler.run(null, null, System.err, "-cp", getClasspath(classLoader), resource.getPath());
        if (compilationResult == 0) {
            LOG.info("Compilation is successful");
        } else {
            throw new JOnLiveException("Compilation Failed");
        }
        return classLoader;
    }

    private void writeJavaClass(String importCode, String codeToExecute) throws JOnLiveException {
        try {
            String importStatements = IOUtils.toString(getClass().getResourceAsStream("ExecutorImports.txt"), "UTF-8");
            String begin = IOUtils.toString(getClass().getResourceAsStream("ExecutorBegin.txt"), "UTF-8");
            String end = IOUtils.toString(getClass().getResourceAsStream("ExecutorEnd.txt"), "UTF-8");
            IOUtils.write(importStatements.concat(importCode).concat(begin).concat(codeToExecute).concat(end),  new FileOutputStream(getClass().getResource(".").getFile()+"/SpringExecutor.java"));

        } catch (IOException e) {
            throw new JOnLiveException("can't create the java temp file", e);
        }
    }


    private String getClasspath(URLClassLoader classLoader) {
        StringBuffer sb = new StringBuffer();
        for (URL url : classLoader.getURLs()) {
            sb.append(url.getFile());
            sb.append(";");
        }
        return sb.toString();

    }

}
