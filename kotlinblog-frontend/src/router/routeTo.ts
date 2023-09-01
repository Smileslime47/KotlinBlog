const routeTo={
    home: () => router.push("/Home"),
    about: () => router.push("/About"),
    article: (cid,aid) => router.push("/article/" + cid + "/" + aid),
    archive: (cid) => router.push("/category/" + cid),
}

export default routeTo