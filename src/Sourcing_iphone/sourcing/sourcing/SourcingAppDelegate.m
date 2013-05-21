//
//  SourcingAppDelegate.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "SourcingAppDelegate.h"
#import "WelcomeViewController.h"
#import "StringUtil.h"
#import "ApplicationSet.h"
#import "iVersion.h"
#import "MobileUserService.h"
#import "ResponseVO.h"
#import "JsonParser.h"

@implementation SourcingAppDelegate

@synthesize managedObjectContext = _managedObjectContext;
@synthesize managedObjectModel = _managedObjectModel;
@synthesize persistentStoreCoordinator = _persistentStoreCoordinator;

+ (void)initialize
{
    //set the bundle ID. normally you wouldn't need to do this
    //as it is picked up automatically from your Info.plist file
    //but we want to test with an app that's actually on the store
//    [iVersion sharedInstance].applicationBundleID = @"com.charcoaldesign.rainbowblocks-lite";
    
    //configure iVersion. These paths are optional - if you don't set
    //them, iVersion will just get the release notes from iTunes directly (if your app is on the store)
//    [iVersion sharedInstance].remoteVersionsPlistURL = @"http://charcoaldesign.co.uk/iVersion/versions.plist";
    [iVersion sharedInstance].localVersionsPlistPath = @"versions.plist";
}

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    // Override point for customization after application launch.
    //self.window.backgroundColor = [UIColor whiteColor];
    
    WelcomeViewController *viewcontroller = [[WelcomeViewController alloc] initWithNibName:@"WelcomeViewController" bundle:nil];
    self.window.rootViewController = viewcontroller;
    [self.window makeKeyAndVisible];
    
    //信息推送相关
    [[UIApplication sharedApplication] registerForRemoteNotificationTypes:(UIRemoteNotificationTypeBadge|UIRemoteNotificationTypeSound|UIRemoteNotificationTypeAlert)];
    return YES;
}

- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Saves changes in the application's managed object context before the application terminates.
    [self saveContext];
}

- (void)saveContext
{
    NSError *error = nil;
    NSManagedObjectContext *managedObjectContext = self.managedObjectContext;
    if (managedObjectContext != nil) {
        if ([managedObjectContext hasChanges] && ![managedObjectContext save:&error]) {
             // Replace this implementation with code to handle the error appropriately.
             // abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development. 
            NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
            abort();
        } 
    }
}

#pragma mark - Core Data stack

// Returns the managed object context for the application.
// If the context doesn't already exist, it is created and bound to the persistent store coordinator for the application.
- (NSManagedObjectContext *)managedObjectContext
{
    if (_managedObjectContext != nil) {
        return _managedObjectContext;
    }
    
    NSPersistentStoreCoordinator *coordinator = [self persistentStoreCoordinator];
    if (coordinator != nil) {
        _managedObjectContext = [[NSManagedObjectContext alloc] init];
        [_managedObjectContext setPersistentStoreCoordinator:coordinator];
    }
    return _managedObjectContext;
}

// Returns the managed object model for the application.
// If the model doesn't already exist, it is created from the application's model.
- (NSManagedObjectModel *)managedObjectModel
{
    if (_managedObjectModel != nil) {
        return _managedObjectModel;
    }
    NSURL *modelURL = [[NSBundle mainBundle] URLForResource:@"sourcing" withExtension:@"momd"];
    _managedObjectModel = [[NSManagedObjectModel alloc] initWithContentsOfURL:modelURL];
    return _managedObjectModel;
}

// Returns the persistent store coordinator for the application.
// If the coordinator doesn't already exist, it is created and the application's store added to it.
- (NSPersistentStoreCoordinator *)persistentStoreCoordinator
{
    if (_persistentStoreCoordinator != nil) {
        return _persistentStoreCoordinator;
    }
    
    NSURL *storeURL = [[self applicationDocumentsDirectory] URLByAppendingPathComponent:@"sourcing.sqlite"];
    
    NSError *error = nil;
    _persistentStoreCoordinator = [[NSPersistentStoreCoordinator alloc] initWithManagedObjectModel:[self managedObjectModel]];
    if (![_persistentStoreCoordinator addPersistentStoreWithType:NSSQLiteStoreType configuration:nil URL:storeURL options:nil error:&error]) {
        /*
         Replace this implementation with code to handle the error appropriately.
         
         abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development. 
         
         Typical reasons for an error here include:
         * The persistent store is not accessible;
         * The schema for the persistent store is incompatible with current managed object model.
         Check the error message to determine what the actual problem was.
         
         
         If the persistent store is not accessible, there is typically something wrong with the file path. Often, a file URL is pointing into the application's resources directory instead of a writeable directory.
         
         If you encounter schema incompatibility errors during development, you can reduce their frequency by:
         * Simply deleting the existing store:
         [[NSFileManager defaultManager] removeItemAtURL:storeURL error:nil]
         
         * Performing automatic lightweight migration by passing the following dictionary as the options parameter:
         @{NSMigratePersistentStoresAutomaticallyOption:@YES, NSInferMappingModelAutomaticallyOption:@YES}
         
         Lightweight migration will only work for a limited set of schema changes; consult "Core Data Model Versioning and Data Migration Programming Guide" for details.
         
         */
        NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
        abort();
    }    
    
    return _persistentStoreCoordinator;
}

#pragma mark - Application's Documents directory

// Returns the URL to the application's Documents directory.
- (NSURL *)applicationDocumentsDirectory
{
    return [[[NSFileManager defaultManager] URLsForDirectory:NSDocumentDirectory inDomains:NSUserDomainMask] lastObject];
}


#pragma mark - 信息推送相关

-(void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken
{
    NSString *deviceTokenStr = [NSString stringWithFormat:@"%@",deviceToken];
    
    //modify the token, remove the  "<, >"
    if ([StringUtil isEmptyStr:deviceTokenStr]) {
        return;
    }
    
    deviceTokenStr = [deviceTokenStr substringFromIndex:1];
    deviceTokenStr = [deviceTokenStr substringToIndex:[deviceTokenStr length]-1];
    //并且去掉空格
    deviceTokenStr = [deviceTokenStr stringByReplacingOccurrencesOfString:@" " withString:@""];
    [[ApplicationSet shareData] setDeviceToken:deviceTokenStr];
    NSLog(@"My device Token is:%@",deviceTokenStr);
    [self setActivityTabBadge:3];
}

-(void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error
{
    NSLog(@"Failed to get token, error:%@",error);
}

//点击推送信息或程序在运行中接收到推送信息时被调用
-(void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo
{
    if(userInfo==NULL||[userInfo objectForKey:@"aps"]==NULL||[[userInfo objectForKey:@"aps"] objectForKey:@"badge"]==NULL)
        return;
    NSString * badge= [NSString stringWithFormat:@"%@",
                       [[userInfo objectForKey:@"aps"] objectForKey:@"badge"]];
    if (![StringUtil isEmptyStr:badge]) {
        [self setActivityTabBadge: [badge intValue]];
        [self updateUserInfo];
    }
    
    NSLog(@"received badge number ---%@ ----",[[userInfo objectForKey:@"aps"] objectForKey:@"badge"]);
    for (id key in userInfo) {
        NSLog(@"key: %@, value: %@", key, [userInfo objectForKey:key]);
    }
    NSLog(@"the badge number is  %d",  [[UIApplication sharedApplication] applicationIconBadgeNumber]);
    NSLog(@"the application  badge number is  %d",  application.applicationIconBadgeNumber);
    //application.applicationIconBadgeNumber += 1;
    // We can determine whether an application is launched as a result of the user tapping the action
    // button or whether the notification was delivered to the already-running application by examining
    // the application state.
    //当程序运行时收到提示信息时弹出提示信息
    if (application.applicationState == UIApplicationStateActive) {
        application.applicationIconBadgeNumber = 0;
        // Nothing to do if applicationState is Inactive, the iOS already displayed an alert view.
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"温馨提示"
                                                            message:[NSString stringWithFormat:@"\n%@",
                                                                     [[userInfo objectForKey:@"aps"] objectForKey:@"alert"]]
                                                           delegate:self
                                                  cancelButtonTitle:@"确定"
                                                  otherButtonTitles:nil];
        
        [alertView show];
    }
}

-(void)setActivityTabBadge:(NSInteger)num
{
    UITabBarController *tabBarController = self.window.rootViewController.tabBarController;
    if(tabBarController==NULL || [tabBarController.viewControllers count]<3)
        return;
    //处理：提醒有未读取的提醒
    UIViewController *viewController = [tabBarController.viewControllers objectAtIndex:2];
    if(viewController!=NULL)
        viewController.tabBarItem.badgeValue = [NSString stringWithFormat:@"%d", num];
    [self updateUserInfo];
}


//更新用户信息
-(void)updateUserInfo
{
    UserVO *uservo = [[ApplicationSet shareData] getUserVO];
    if(uservo==nil)
        return;
    MobileUserService *service = [[MobileUserService alloc] initWithDelegate:self tag:CINT_TAG_UPDATEUSERINFO];
    [service userLogin:uservo.useraccount password:uservo.password];
}

-(void)serviceResult:(ResponseVO *)result
{
    if (result==nil) {
        return;
    }
    if(result.tag == CINT_TAG_UPDATEUSERINFO)
    {
        if([result isSucess])
        {
            ResponseVO *resp = [[ResponseVO alloc] init];
            UserVO *uservo = [JsonParser parseJsonToEntity:result.msg respVO:&resp ref:nil];
            if([resp isSucess])
            {
                [[ApplicationSet shareData] setLoginUserInfo:uservo saveinfo:YES];
            }
            else
            {
                NSLog(@"%@ updateUserInfo JsonParser parseJsonToEntity error:%@",[[self class] description],result.msg);
            }
        }
        else
        {
            NSLog(@"%@ updateUserInfo error:%@",[[self class] description],result.msg);
        }
    }
}
@end
