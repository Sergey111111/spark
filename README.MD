
##Note
This applicatoin provide test api for spark equation  test task

runs on port 8080 (http) with context path ' '

It's better to use only reference type 
in order to let Hibernate work properly
Actually template does't look as typical get-used-to-do 
for java so it cause additional difficulties make it work fine.

Usually I add one more extra layer controller request and service
Dto layer. So  the reason I didn't add the layer this time is
the big growth of code debugging and tests.
This layer contains of immutable object and transformer.
Validation is also here but instead of doing this 
I attach it to the model layer witch is no good.
It's not big deal to make everything properly, but it take much time
of copypasting boilerplate code and fixing hibernate errors by the way.

Lots of error responses comes with pretty user-friendly view so that's because
I used GlobalHandler for catching exceptions

To test this project you can use postman collection witch is provided
in root directory as JSON file. It's very easy to run it query by query
to check how the application works.

I also use Lombok framework to shorten all the boilerplate pojo code.
It cause code generation and that's why some code may have awkward construction


