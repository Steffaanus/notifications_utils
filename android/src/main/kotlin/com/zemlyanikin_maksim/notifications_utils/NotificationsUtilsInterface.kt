// Autogenerated from Pigeon (v10.1.3), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.zemlyanikin_maksim.notifications_utils

import android.util.Log
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

private fun wrapResult(result: Any?): List<Any?> {
  return listOf(result)
}

private fun wrapError(exception: Throwable): List<Any?> {
  if (exception is FlutterError) {
    return listOf(
      exception.code,
      exception.message,
      exception.details
    )
  } else {
    return listOf(
      exception.javaClass.simpleName,
      exception.toString(),
      "Cause: " + exception.cause + ", Stacktrace: " + Log.getStackTraceString(exception)
    )
  }
}

/**
 * Error class for passing custom error details to Flutter via a thrown PlatformException.
 * @property code The error code.
 * @property message The error message.
 * @property details The error details. Must be a datatype supported by the api codec.
 */
class FlutterError (
  val code: String,
  override val message: String? = null,
  val details: Any? = null
) : Throwable()

/** Generated class from Pigeon that represents data sent in messages. */
data class NotificationId (
  val androidId: Long? = null,
  val iosId: String? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): NotificationId {
      val androidId = list[0].let { if (it is Int) it.toLong() else it as Long? }
      val iosId = list[1] as String?
      return NotificationId(androidId, iosId)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      androidId,
      iosId,
    )
  }
}

/**
 * Notification payload class.
 *
 * `UNNotificationContent` on iOS.
 *
 * Generated class from Pigeon that represents data sent in messages.
 */
data class DeliveredNotification (
  val id: NotificationId,
  val body: String,
  val title: String,
  val subtitle: String,
  val threadIdentifier: String,
  /**
   * Notification payload.
   *
   * Usually a map of strings to some primitive types.
   */
  val payload: Map<Any, Any?>

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): DeliveredNotification {
      val id = NotificationId.fromList(list[0] as List<Any?>)
      val body = list[1] as String
      val title = list[2] as String
      val subtitle = list[3] as String
      val threadIdentifier = list[4] as String
      val payload = list[5] as Map<Any, Any?>
      return DeliveredNotification(id, body, title, subtitle, threadIdentifier, payload)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      id.toList(),
      body,
      title,
      subtitle,
      threadIdentifier,
      payload,
    )
  }
}

@Suppress("UNCHECKED_CAST")
private object NotificationsUtilsCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          DeliveredNotification.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          NotificationId.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is DeliveredNotification -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is NotificationId -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/** Generated interface from Pigeon that represents a handler of messages from Flutter. */
interface NotificationsUtils {
  fun getDeliveredNotifications(callback: (Result<List<DeliveredNotification>>) -> Unit)
  fun removeDeliveredNotifications(ids: List<NotificationId>)

  companion object {
    /** The codec used by NotificationsUtils. */
    val codec: MessageCodec<Any?> by lazy {
      NotificationsUtilsCodec
    }
    /** Sets up an instance of `NotificationsUtils` to handle messages through the `binaryMessenger`. */
    @Suppress("UNCHECKED_CAST")
    fun setUp(binaryMessenger: BinaryMessenger, api: NotificationsUtils?) {
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.NotificationsUtils.getDeliveredNotifications", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            api.getDeliveredNotifications() { result: Result<List<DeliveredNotification>> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.NotificationsUtils.removeDeliveredNotifications", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val idsArg = args[0] as List<NotificationId>
            var wrapped: List<Any?>
            try {
              api.removeDeliveredNotifications(idsArg)
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
