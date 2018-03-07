# Android Helper

Android helper class to create plugins for Unity3d (JAR or AAR)

For callback ,used :  UnityPlayer.UnitySendMessage("GameObjectName", "MethodName", "Message");

## Important

Unity already includes the contents of classes.jar when it builds anyway so even one plugin including it again means you have two copies and Unity's build tools cannot resolve that

Unity will automatically contain its own classes.jar when packing apk, so need to use compression software (I use WINRar) to open aar file to delete classes.jar file under libs directory