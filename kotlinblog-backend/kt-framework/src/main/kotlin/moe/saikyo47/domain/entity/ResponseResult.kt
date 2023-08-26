package moe.saikyo47.domain.entity

import com.fasterxml.jackson.annotation.JsonInclude
import moe.saikyo47.enums.AppHttpCodeEnum
import java.io.Serializable


@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseResult<T> : Serializable {
    var code: Int = 200
    var msg: String? = null
    var data: T? = null

    constructor() {
        this.code = AppHttpCodeEnum.SUCCESS.code
        msg = AppHttpCodeEnum.SUCCESS.msg
    }

    constructor(code: Int, data: T) {
        this.code = code
        this.data = data
    }

    constructor(status: AppHttpCodeEnum, data: T) {
        this.code = status.code
        this.data = data
    }

    constructor(code: Int, msg: String?, data: T) {
        this.code = code
        this.msg = msg
        this.data = data
    }

    constructor(status: AppHttpCodeEnum, msg: String?, data: T) {
        this.code = status.code
        this.msg = msg
        this.data = data
    }

    constructor(code: Int, msg: String?) {
        this.code = code
        this.msg = msg
    }

    constructor(status: AppHttpCodeEnum, msg: String?) {
        this.code = status.code
        this.msg = msg
    }
}