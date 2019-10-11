## Springboot AWS archetype
A maven archetype for Springboot projects running on AWS CodePipeline

#### Step 1 : checkout the project 
```
git clone https://github.com/mbakkali/springboot-aws-archetype.git
```

#### Step 2 : install the archetype locally
```
mvn clean install
```
By running the **mvn install** command the template will be added to your local repo

#### Step 3 : excecute the archetype
Go to a new folder 
```
mvn archetype:generate -DarchetypeCatalog=local
```

You will be asked to select an archetype from the list. If you don't have other archetypes installed you'll have to select 1  
You will be asked to enter different properties (port, artifact id ...)  
A folder will appear where you ran the command

#### Step 4 : run the generated project
Go to the generated folder (Its name is the artifactId chosen)
```
mvn clean install
java -jar -Dspring.active.profiles=local target/yourartifact.jar
```

#### And then 
- Setup a git repository for your new project
- Analyze the folder on sonarcloud.io
- Create a new pipeline on AWS wih CodePipeline