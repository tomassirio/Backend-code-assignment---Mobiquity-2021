## Mobiquity-Api-Challenge
### Maven Library Repository

## :star: Getting started
### :computer: Running the project

- This library can be imported to your project by importing the .jar file 
  to your resources or via maven dependency like this:
  
```maven
<dependency>
    <groupId>com.mobiquity</groupId>
    <artifactId>implementation</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```
- The Packer class has a public static method with the signature:
 _pack(String path)_ where path is the direction of the input file.
- This method throws an APIException if there's any issue with the path or the constraints.

### Package Challenge Solution

1. In order to create the new Items and Packages there are two factories with the factory pattern 
which create every new POJO.

2. Each of the POJOs were designed as DTO with their corresponding restrictions.

3. There are two services for the File and Parsing Handling with their respective interfaces and implementations.

4. These services are instantiated as Singletons since we don't need more than one instance for any of them

5. As for the Packer itself, the solution was developed through a Dynamic Programming method which takes O(n*W) 
   (being n the amount of items and W the capacity of the package) to be solved. Every single method has it's corresponding
   documentation attached to it.
   
6. Testing was developed with PowerMock

## Known issues ##

1- There's an ignored test on the Packer File because I had an issue mocking the Validator and ValidatorFactory
on the ParseServiceImpl instance

2- For the same aforementioned reason, there's an ignored Test on the ParseServiceImplTest

3- On the PackDP solution, I'm not returning the option that has the lowest weight. I couldn't find a way
on my algorithm that could keep it's complexity and return that solution as well

## :bust_in_silhouette: Who Am I?

<img src="https://media.discordapp.net/attachments/763140054825697301/763681938652528690/logo-design-branding-logo-tool-open-electronic-1-5f7ed02bc8247.png?width=468&height=468" width="410" height="410" /></p>

  <a href="mailto:tomassirio@gmail.com?Subject=Tomas%20You%20Are%20Amazing!">
      <img src="https://cdn2.downdetector.com/static/uploads/logo/image21.png" width="100"; height="100"/>
  </a>
  <a href="https://www.linkedin.com/in/tomassirio/">
      <img src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimage.flaticon.com%2Ficons%2Fpng%2F512%2F174%2F174857.png&f=1&nofb=1" width="100"; height="100"/>
  </a>
  <a href="https://dev.to/tomassirio">
      <img src="https://avatars3.githubusercontent.com/u/13521919?s=280&v=4" width="100"; height="100"/>
  </a>
  <a href="https://www.buymeacoffee.com/tomassirio1">
      <img src="https://i.pinimg.com/originals/60/fd/e8/60fde811b6be57094e0abc69d9c2622a.jpg" width="100"; height="100"/>
  </a>