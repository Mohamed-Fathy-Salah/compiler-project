<h1 align="center">ANTLRv4 Compiler</h1>

## Table of contents

- [Quick start](#Quick-start)
- [idea](#idea)
- [samples](#samples)
- [how to use](#how-to-use)


## Quick start
- Clone the repo: `git clone https://github.com/Mohamed-Fathy-Salah/compiler-project`
- install intelliJ IDEA.
- Install ANTLRv4. you can install ANTLR from IDEA's plugin manager.
- Import antlr runtime jar [Download](https://repo1.maven.org/maven2/org/antlr/antlr4/4.9.3/antlr4-4.9.3-complete.jar).

## idea
in our project (this repo), we build code analyzer that helps in detect code blocks in the input java program file then statically test if this blocks will work or not then produce html file as output that highlighted this blocks by green if this blocks will works or red if not. 
there is a third case in branches (if/else/for/while) which has more than one condition in OR (||) expression, and their some condition will be false and some be true then highlight the boolean expression by yellow but the block will be  highlighted by green as it will excuted.

## samples
![sample1](samples/sample1.jfif)
![sample2](samples/sample2.jfif)
![sample1](samples/sample3.jfif)

## how to use
1. build project as jar file ( save as out/artifacts/project_jar/project.jar ).
2. put your example in examples folder and name it `Examplex.java` where x is a number.
3. $ sh run.sh
