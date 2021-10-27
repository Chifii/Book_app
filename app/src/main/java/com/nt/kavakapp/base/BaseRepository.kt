package com.nt.kavakapp.base

import com.nt.kavakapp.base.RepositoryFactory.getRetrofit

open class BaseRepository<T>(
    service: Class<T>,
) {
    var service: T = getRetrofit()
        .create(service)
}