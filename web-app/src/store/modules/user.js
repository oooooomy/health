const state = {
    details: JSON.parse(localStorage.getItem("user"))
}

const getters = {}

const mutations = {

    saveLoginUser(state, user) {
        state.details = user
        localStorage.setItem("user", JSON.stringify(user))
    },

    userLogout(state) {
        state.details = null
        localStorage.removeItem("user")
    }

}

const actions = {}

export default {
    namespaced: true,
    state,
    getters,
    mutations,
    actions
}