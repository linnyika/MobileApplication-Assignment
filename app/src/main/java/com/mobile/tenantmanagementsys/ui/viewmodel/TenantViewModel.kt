package com.mobile.tenantmanagementsys.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.mobile.tenantmanagementsys.data.local.TenantDatabase
import com.mobile.tenantmanagementsys.data.model.Tenant
import com.mobile.tenantmanagementsys.data.repository.TenantRepository
import kotlinx.coroutines.launch

class TenantViewModel(application: Application) : AndroidViewModel(application) {

    // Connect database → DAO → Repository
    private val repository = TenantRepository(
        TenantDatabase.getDatabase(application).tenantDao()
    )

    // Observe tenants stored in Room
    val tenants: LiveData<List<Tenant>> = repository.tenants

    // Display tenant count
    val tenantCountText: LiveData<String> = tenants.map { list ->
        "Tenants\n${list.size}"
    }

    // Calculate total rent
    val totalRentText: LiveData<String> = tenants.map { list ->
        val total = list.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
        "Total rent\nKSh %,.0f".format(total)
    }

    // Display all tenants
    val allTenantsText: LiveData<String> = tenants.map { list ->
        if (list.isEmpty()) {
            "No tenants yet. Add one from the Dashboard."
        } else {
            list.joinToString("\n\n") { it.summary() }
        }
    }

    // Add a tenant
    fun addTenant(name: String, phone: String, rent: String): Tenant? {

        val cleanName = name.trim()
        val cleanPhone = phone.trim()
        val cleanRent = rent.trim()

        if (cleanName.isEmpty() || cleanPhone.isEmpty() || cleanRent.isEmpty()) {
            return null
        }

        val tenant = Tenant(cleanName, cleanPhone, cleanRent)

        // Save tenant in Room using a coroutine
        viewModelScope.launch {
            repository.addTenant(tenant)
        }

        return tenant
    }
}