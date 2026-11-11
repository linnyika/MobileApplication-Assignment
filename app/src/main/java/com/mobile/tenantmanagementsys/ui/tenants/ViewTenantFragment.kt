package com.mobile.tenantmanagementsys.ui.tenants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mobile.tenantmanagementsys.databinding.FragmentViewTenantsBinding
import android.content.Intent
import android.net.Uri
import com.mobile.tenantmanagementsys.data.repository.TenantRepository

class ViewTenantsFragment : Fragment() {

    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!

    // Receive the arguments passed through Safe Args.
    private val args: ViewTenantsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTenantsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Receive the newly added tenant, if one was passed.
        val newTenant = args.newTenant

        // Display the new tenant using Data Binding.
        binding.tenant = newTenant

        // Hide the new-tenant section when no tenant was passed.
        if (newTenant == null) {
            binding.newTenantGroup.visibility = View.GONE
        }
        binding.callButton.setOnClickListener {
            if (newTenant != null) {
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:${newTenant.phone}")
                )
                startActivity(intent)
            }
        }
        // Display every tenant stored in the repository.
        val tenants = TenantRepository.tenants

        if (tenants.isEmpty()) {
            binding.allTenantsTextView.text =
                "No tenants yet. Add one from the Dashboard."
        } else {
            binding.allTenantsTextView.text =
                tenants.joinToString("\n\n") { it.summary() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
