//
//  NetWorkUtil.m
//  EmbaClientForiPhone
//
//  Created by wuzhu on 12-6-7.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "NetWorkUtil.h"
#import "Reachability.h"
#import "DataInterfaceUtil.h"
#import "iToast.h"

@implementation NetWorkUtil

/**
 * 是否能连接到服务器
 */
+(BOOL) activeInternetNetWork
{
    Reachability *r = [Reachability reachabilityForInternetConnection];
    NetworkStatus internetNetWorkStatus = [r currentReachabilityStatus];
    if(internetNetWorkStatus==NotReachable)
        return NO;
    return YES;
}


//是否能连接到网络
+(BOOL) activeHostNetWork
{
    NSString *hostUrl = [DataInterfaceUtil getDataInterface:@"jsonurl"];
    Reachability *r = [Reachability reachabilityWithHostName:hostUrl];
    NetworkStatus hostNetWorkStatus = [r currentReachabilityStatus];
    if(hostNetWorkStatus==NotReachable)
        return NO;
    return YES;
}


/**
 * 检查网络状态
 * @param showHit 是否在无网络时进行提示
 */
+ (BOOL) checkNetWork:(BOOL) showHit
{
    if (![NetWorkUtil activeInternetNetWork]) 
    {
        //提示无网络
        if(showHit)
            [[iToast makeText:NSLocalizedString(@"No network is currently.", @"提示本地无网络")] show];
        return NO;
        
    }
//    else if(![NetWorkUtil activeHostNetWork])
//    {
//        //提示无法连接服务器
//        if(showHit)
//            [[iToast makeText:NSLocalizedString(@"Currently unable to access the server.", @"提示无法连接服务器")] show];
//        return NO;
//    }
    return YES;
}

@end
