//
//  ApplicationSet.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ApplicationSet.h"

@implementation ApplicationSet

@synthesize isRegDevToken=_isRegDevToken;
@synthesize deviceToken = _deviceToken;

static ApplicationSet *_shareData = nil;

+(ApplicationSet *)shareData
{
    @synchronized([ApplicationSet class])
    {
        if(!_shareData)
        {
            _shareData = [[ApplicationSet alloc] init];
        }
        return _shareData;
    }
}

@end
