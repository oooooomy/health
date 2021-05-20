import service from "../utils/request";

export function FindAllUser() {
    return service({
        url: '/user',
        method: 'get'
    })
}