package com.davai.merit

class Person {

    String emailAddress
    String name
    String password
    String favoriteColor

    static constraints = {
        emailAddress()
        name()
        favoriteColor(nullable:true)
    }
    
    public String toString() {
        return id + ": " + this.name
    }
}
