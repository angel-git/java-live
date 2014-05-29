java-live
=========

Execute java code on runtime

# WTF
This is an small test to see how execute java code in your running server. You shouldn't use this in any production environment.
You can test it yourself by cloning this repo and run: mvn clean jetty:run

the variable *ctx* contains your WebApplicationContext. You could do something like:
> ctx.getBean("helloService", HelloService.class).saySomething("test hello");


# how it works

Basically the class *CodeExecutor* will take the import statements, the code you want to execute and the servlet request and it will create a new java class, compile it and run it for you.

