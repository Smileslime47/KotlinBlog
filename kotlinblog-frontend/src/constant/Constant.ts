const Constant = {
    BASE_URL: import.meta.env.VITE_BASE_URL,
    ASSETS_URL: import.meta.env.VITE_ASSETS_URL,
    category: {
        api: "/category",
        getSubCategories: "/sub-categories",
        getRootCategories: "/root-categories",
        getRootCategory: "/root-category",
    },
    article: {
        api: "/article",
        getInfoByCategory: "/info/category",
        getInfoById: "/info/article",
        getDetailById: "/detail/article"
    },
    login: "/login",
}
export default Constant