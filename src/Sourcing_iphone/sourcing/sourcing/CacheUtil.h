//
//  CacheUtil.h
//  sourcing
//
//  Created by wuzhu_mac on 13-5-21.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ChannelVO.h"

@interface CacheUtil : NSObject
+(BOOL)cacheContent:(ChannelVO *)cvo content:(NSString *)kcontent;
+(NSString *)getCacheContent:(ChannelVO *)cvo;
@end
