package moe.saikyo47.constant

class Constant {
    class Environment {
        companion object {
            const val RESOURCE_PATH = "D:\\blog\\KotlinBlog\\posts"
        }
    }

    class ApiPath {
        companion object {
            const val CATEGORY_API = "/api/category"
            const val CATEGORY_ROOT_CATEGORIES = "/root-categories"
            const val CATEGORY_SUB_CATEGORIES = "/sub-categories"
            const val CATEGORY_ROOT_CATEGORY = "/root-category"

            const val ARTICLE_API = "/api/article"
            const val ARTICLE_INFO_BY_CATEGORY = "/info/category"
            const val ARTICLE_INFO_BY_ID = "/info/article"
            const val ARTICLE_DETAIL_BY_ID = "/detail/article"
        }
    }

    class DataField {
        companion object {
            const val ARTICLE_ID = "id"
            const val ARTICLE_TITLE = "title"
            const val ARTICLE_CONTENT = "content"
            const val ARTICLE_SUMMARY = "summary"
            const val ARTICLE_CATEGORY = "category"

            const val CATEGORY_ID = "id"
            const val CATEGORY_IS_AVAILABLE = "is_available"
            const val CATEGORY_PARENT = "parent"

            const val USER_USER_NAME = "user_name"
            const val USER_PASSWORD = "password"
        }
    }

    companion object {
        const val CATEGORY_IS_AVAILABILE = 1
        const val CATEGORY_IS_NOT_AVAILABILE = 0
        const val ARTICLE_IS_RELEASED = 1
        const val ARTICLE_IS_NOT_RELEASED = 0
    }
}