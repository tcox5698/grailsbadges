package com.davai.merit

class Category implements Serializable {

    String name

    static constraints = {
        name(unique:true)
    }
    
    public String toString() {
        return "Category: [id: " + id + ", name:" + this.name + "]"
    }
}
