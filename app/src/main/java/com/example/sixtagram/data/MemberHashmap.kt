package com.example.sixtagram.data

object MemberHashmap {
    private val memberHashMap = HashMap<String, Int>()

    fun memberHash(){
        memberHashMap["a"] = 0
        memberHashMap["b"] = 1
        memberHashMap["c"] = 2
        memberHashMap["d"] = 3
        memberHashMap["e"] = 4
    }

    fun memberHashAdd(add: String, index: Int){
        memberHashMap[add] = index
    }

    fun retrieveAllMembers(): Map<String, Int> {
        return memberHashMap
    }

    fun memberHashDel(del: String){
        memberHashMap.remove(del)
    }

    fun retrieveMemberByEmail(email: String): Int? {
        return memberHashMap[email]
    }
}