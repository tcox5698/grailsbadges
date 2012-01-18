package com.davai.merit

class Category {

    String name

    static constraints = {
        name(unique:true)
    }
    
    public String toString() {
        return id + ": " + this.name
    }
}
