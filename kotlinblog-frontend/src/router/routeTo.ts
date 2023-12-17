import router from "~/router/router";

const routeTo = {
    home: () => router.push("/Home"),
    about: () => router.push("/About"),
    article: (cid, aid) => router.push("/article/" + cid + "/" + aid),
    archive: (cid) => router.push("/category/" + cid),
    login: () => router.push("/Login"),
}

export default routeTo