del *.class
del *.tokens
del *.interp
del P03SchieweBaseListener.java
del P03SchieweLexer.java
del P03SchieweListener.java
del P03SchieweParser.java

antlr4 P03Schiewe.g4

javac *.java