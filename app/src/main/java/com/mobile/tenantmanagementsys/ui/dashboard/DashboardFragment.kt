package com.mobile.tenantmanagementsys.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mobile.tenantmanagementsys.ui.viewmodel.TenantViewModel
import androidx.navigation.fragment.findNavController
import com.mobile.tenantmanagementsys.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    //The SHARED ViewModel: every Fragment in MainActivity gets the SAME one
    private val viewModel: TenantViewModel by activityViewModels()

    // 1. The Fragment object is created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onCreate")
    }

    // 2. Build the view from fragment_dashboard.xml.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onCreateView")

        _binding = FragmentDashboardBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    // 3. The view exists. Set the metrics and button listeners.
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onViewCreated")

        // DATA BINDING: give the ViewModel to <variable name="viewModel">
        binding.viewModel = viewModel

// Let the layout WATCH LiveData (otherwise the text never updates)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.addTenantButton.setOnClickListener {
            val action =
                DashboardFragmentDirections
                    .actionDashboardFragmentToAddTenantFragment()

            findNavController().navigate(action)
        }

        binding.viewTenantsButton.setOnClickListener {
            val action =
                DashboardFragmentDirections
                    .actionDashboardFragmentToViewTenantsFragment()

            findNavController().navigate(action)
        }
    }

    // 4. The Fragment becomes visible.
    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onStart")
    }

    // 5. The user can interact with the Fragment.
    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onResume")
    }

    // 6. The Fragment is leaving the foreground.
    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onPause")
    }

    // 7. The Fragment is no longer visible.
    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onStop")
    }

    // 8. The view is destroyed, but the Fragment may remain alive.
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onDestroyView")
        _binding = null
    }

    // 9. The Fragment object itself is destroyed.
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "DashboardFragment: onDestroy")
    }
}