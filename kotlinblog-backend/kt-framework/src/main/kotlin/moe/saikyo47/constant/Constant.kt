package moe.saikyo47.constant

class Constant {
    class Environment {
        companion object {
            const val RESOURCE_PATH = "D:\\blog\\KotlinBlog\\posts"
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