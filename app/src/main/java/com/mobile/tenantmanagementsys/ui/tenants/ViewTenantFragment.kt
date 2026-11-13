package com.mobile.tenantmanagementsys.ui.tenants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.fragment.app.activityViewModels
import com.mobile.tenantmanagementsys.ui.viewmodel.TenantViewModel
import com.mobile.tenantmanagementsys.databinding.FragmentViewTenantsBinding
import android.content.Intent
import android.net.Uri

class ViewTenantsFragment : Fragment() {

    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!

    // Receive the arguments passed through Safe Args.
    private val args: ViewTenantsFragmentArgs by navArgs()
    // The SHARED ViewModel: every Fragment in MainActivity gets the SAME one
    private val viewModel: TenantViewModel by activityViewModels()

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
        // Display every tenant stored in the repository. OBSERVER: runs now, and again every time allTenantsText changes
        viewModel.allTenantsText.observe(viewLifecycleOwner) { text ->
            binding.allTenantsTextView.text = text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
