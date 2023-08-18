package com.example.sixtagram.memberData

object MemberHashmap {
    private val memberHashMap = HashMap<String, Int>()

    fun memberHash(){
        memberHashMap["a"] = 0
        memberHashMap["b"] = 1
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