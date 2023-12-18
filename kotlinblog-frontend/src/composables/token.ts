
const token = {
    updateToken : (accessToken:string)=> {
        window.localStorage.setItem('jwtToken', accessToken)
    },
    removeToken : () => {
        window.localStorage.removeItem("jwtToken")
    }
}

export default token