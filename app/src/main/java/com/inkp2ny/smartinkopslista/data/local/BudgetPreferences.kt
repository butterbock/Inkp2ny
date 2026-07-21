package com.inkp2ny.smartinkopslista.data.local

import android.content.Context

/** Sparar användarens totala inköpsbudget lokalt via SharedPreferences. */
class BudgetPreferences(context: Context) {

    private val prefs = context.applicationContext
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getBudget(): Double = prefs.getFloat(KEY_TOTAL_BUDGET, DEFAULT_BUDGET).toDouble()

    fun setBudget(value: Double) {
        prefs.edit().putFloat(KEY_TOTAL_BUDGET, value.toFloat()).apply()
    }

    companion object {
        private const val PREFS_NAME = "budget_prefs"
        private const val KEY_TOTAL_BUDGET = "total_budget"
        private const val DEFAULT_BUDGET = 1000f
    }
}
