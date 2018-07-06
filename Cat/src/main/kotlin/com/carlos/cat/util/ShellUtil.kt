package com.carlos.cat.util

import com.carlos.cat.ThreadPoolManager
import java.io.*

/**
 * Created by Carlos on 2018/6/26.
 */
object ShellUtil {

    fun executeCommand(command: String): CommandResult {
        return executeCommand(command, true)
    }

    fun executeCommand(command: String, isNeedResponse: Boolean): CommandResult {

        var process: Process? = null
        var successResult: BufferedReader? = null
        var errorResult: BufferedReader? = null
        var successMessage: MutableList<String> = mutableListOf()
        var errorMessage: MutableList<String> = mutableListOf()

        lateinit var dataOutputStream: DataOutputStream

        try {
            process = Runtime.getRuntime().exec(command)
            dataOutputStream = DataOutputStream(process.outputStream)
            dataOutputStream.flush()

//            result = process.waitFor()
            if (isNeedResponse) {
//
                var string: String? = null

                ThreadPoolManager.newTask({
                    errorResult = BufferedReader(InputStreamReader(process.errorStream))
                    while ((errorResult?.readLine().also { string = it }) != null) {
                        errorMessage.add(string!!)
                    }
                })


                successResult = BufferedReader(InputStreamReader(process.inputStream))
                while ((successResult.readLine().apply { string = this }) != null) {
                    successMessage.add(string!!)
                }

            }

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                if (errorResult != null) {
                    errorResult?.close()
                }
                if (successResult != null) {
                    successResult?.close()
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (process != null) {
                    process.destroy()
                }
            }
        }

        return CommandResult(successMessage, errorMessage)

    }


    class CommandResult(var responseMessage: List<String>, var errorMessage: List<String>)

}