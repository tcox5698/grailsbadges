package com.davai.merit

import com.davai.secure.*

class Person extends SecUser {

    String name

    static constraints = {
        name(unique:true)
    }
    
    public String toString() {
        return id + ": " + this.name
    } 
    
}
