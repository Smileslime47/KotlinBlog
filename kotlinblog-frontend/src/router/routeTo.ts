import router from "~/router/router";

const routeTo = {
    home: () => router.push("/Home"),
    about: () => router.push("/About"),
    article: (cid, aid) => router.push("/article/" + cid + "/" + aid),
    archive: (cid) => router.push("/category/" + cid),
    login: () => router.push("/Login"),
    loadAndTo: (action:()=>void)=>{
        router.push("/loading")
        action()
    },
    fresh:()=>{router.go(0)},
    back:() => router.go(-1)
}

export default routeTo