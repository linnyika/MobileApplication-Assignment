package com.mobile.tenantmanagementsys.ui.tenants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobile.tenantmanagementsys.data.model.Tenant
import com.mobile.tenantmanagementsys.data.repository.TenantRepository
import com.mobile.tenantmanagementsys.databinding.FragmentAddTenantBinding

class AddTenantFragment : Fragment() {

    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTenantBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.saveButton.setOnClickListener {

            // Read the details entered by the user.
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            // Make sure all fields have been filled.
            if (name.isEmpty() || phone.isEmpty() || rent.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Create the tenant object.
            val tenant = Tenant(name, phone, rent)

            // Store the tenant in the shared repository.
            TenantRepository.addTenant(tenant)

            // Generate the Safe Args action and pass the tenant.
            val action =
                AddTenantFragmentDirections
                    .actionAddTenantFragmentToViewTenantsFragment(tenant)

            // Navigate to View Tenants.
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}