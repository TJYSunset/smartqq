package com.scienjus.smartqqkotlin.model

import com.alibaba.fastjson.JSONObject
import com.scienjus.smartqqkotlin.client.SmartQqClient

/**
 * 消息.
 * @author ScienJus
 * @author [Liang Ding](http://88250.b3log.org)
 * @date 15/12/19.
 */
data class FriendMessage internal constructor(
        val client: SmartQqClient,
        internal val senderId: Long = 0,
        override val content: String,
        override val timestamp: Long) : Message {
    override val sender: User
        get() = TODO() // TODO

    override fun reply(content: String) {
        client.message(SmartQqClient.TargetType.FRIEND, senderId, content)
    }

    override val replyableTarget: MessageTarget
        get() = TODO() // TODO

    internal constructor(client: SmartQqClient, obj: JSONObject) : this(
            client,
            obj.getLongValue("from_uin"),
            obj.getJSONArray("content").drop(1).joinToString(),
            obj.getLongValue("time"))
}