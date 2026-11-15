package com.mobile.tenantmanagementsys.data.repository

import androidx.lifecycle.LiveData
import com.mobile.tenantmanagementsys.data.local.TenantDao
import com.mobile.tenantmanagementsys.data.model.Tenant

class TenantRepository(private val tenantDao: TenantDao) {

    // Gets all tenants from the Room database
    val tenants: LiveData<List<Tenant>> = tenantDao.getAllTenants()

    // Saves a tenant into the Room database
    suspend fun addTenant(tenant: Tenant) {
        tenantDao.insertTenant(tenant)
    }
}