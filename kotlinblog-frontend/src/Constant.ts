const Constant = {
    BASE_URL: import.meta.env.VITE_BASE_URL,
    category: {
        api: "/api/category",
        getSubCategories:"/sub-categories",
        getRootCategories:"/root-categories",
    },
    article: {
        api: "/api/article",
        getInfoByCategory:"/info/category",
        getInfoById: "/info/article",
        getDetailById:"/detail/article"
    },
}
export default Constant