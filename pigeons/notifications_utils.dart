import 'package:pigeon/pigeon.dart';

// #docregion config
@ConfigurePigeon(PigeonOptions(
  dartOut: 'lib/src/pigeons/notifications_utils.gen.dart',
  swiftOut: 'ios/Classes/NotificationsUtilsInterface.swift',
  kotlinOut: 'android/src/main/kotlin/com/zemlyanikin_maksim/notifications_utils/NotificationsUtilsInterface.kt',
  kotlinOptions: KotlinOptions(package: 'com.zemlyanikin_maksim.notifications_utils'),
))
// #enddocregion config

// On android and ios notification id has different types.
class NotificationId {
  NotificationId({
    this.androidId,
    this.iosId,
  });

  final int? androidId;
  final String? iosId;
}

/// Notification payload class.
///
/// `UNNotificationContent` on iOS.
class DeliveredNotification {
  DeliveredNotification({
    required this.id,
    required this.title,
    required this.subtitle,
    required this.body,
    required this.threadIdentifier,
    required this.payload,
  });

  // `UNNotificationContent.identifier` on iOS.
  final NotificationId id;
  final String title;
  final String body;
  final String subtitle;

  // `threadIdentifier` on iOS.
  final String threadIdentifier;

  /// Notification payload.
  ///
  /// Usually a map of strings to some primitive types.
  // `userInfo` on iOS.
  final Map payload;
}

@HostApi()
abstract class NotificationsUtils {
  /// Returns a list of notifications that are shown in the notification center.
  @async
  List<DeliveredNotification> getDeliveredNotifications();

  /// Removes the specified notifications from the notification center.
  void removeDeliveredNotifications(List<NotificationId> ids);
}