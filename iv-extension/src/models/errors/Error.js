import * as ErrorTypes from './ErrorTypes'

export const ErrorCodes = {
    NO_SIGNATURE:402,
    FORBIDDEN:403,
    TIMED_OUT:408,
    LOCKED:423,
    UPGRADE_REQUIRED:426,
    TOO_MANY_REQUESTS:429
};

export default class Error {

    constructor(_type, _message, _code = ErrorCodes.LOCKED){
        this.type = _type;
        this.message = _message;
        this.code = _code;
        this.isError = true;
    }

    static locked(){
        return new Error(ErrorTypes.LOCKED, "The user's wallet is locked.")
    }

    static promptClosedWithoutAction(){
        return new Error(ErrorTypes.PROMPT_CLOSED, "The user closed the prompt without any action.", ErrorCodes.TIMED_OUT)
    }

    static maliciousEvent(){
        return new Error(ErrorTypes.MALICIOUS, "Malicious event discarded.", ErrorCodes.FORBIDDEN)
    }

    static loginError(_type, _message){
        return new Error(_type, _message, ErrorCodes.FORBIDDEN)
    }

    static signatureError(_type, _message){
        return new Error(_type, _message, ErrorCodes.NO_SIGNATURE)
    }

}
