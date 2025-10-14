package com.pierre_marie_marchio.skyted_technical_test.infrastructure.common.bases

import com.pierre_marie_marchio.skyted_technical_test.infrastructure.common.interfaces.ApiClient

abstract class RepositoryBase<DaoT>(
    protected val client: ApiClient? = null,
    protected val dao: DaoT? = null,
) {

    protected fun checkSourceAvailability() {
        if (client == null && dao == null) {
            throw IllegalStateException("Aucun client API ni DAO disponible pour cette requête")
        }
    }

    protected suspend fun <T> fetchFromDaoOrApi(
        fetchFromDao: suspend DaoT.() -> T?,
        fetchFromApi: suspend () -> T
    ): T {
        checkSourceAvailability()

        dao?.let {
            val daoResult = it.fetchFromDao()
            if (daoResult != null) return daoResult
        }

        client?.let {
            return fetchFromApi()
        }

        throw IllegalStateException("Aucune source disponible pour récupérer les données")
    }
}