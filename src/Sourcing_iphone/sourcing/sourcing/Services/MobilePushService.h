//
//  MobilePushService.h
//  sourcing
//
//  Created by wuzhu_mac on 13-5-21.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import "BaseJsonService.h"

@interface MobilePushService : BaseJsonService

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods


-(void)sendDeviceToken:(NSInteger)uid devicetoken:(NSString *)kDeviceToken;

@end
