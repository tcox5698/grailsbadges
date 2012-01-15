package com.davai.merit

class Person {

    String emailAddress
    String name
    String password

    static constraints = {
        emailAddress()
        name()
    }
    
    public String toString() {
        return id + ": " + this.name
    }
}
