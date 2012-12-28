//
//  BaseVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@implementation BaseVO

@synthesize Id = _id;

- (BOOL) isEqual:(id)object
{
    if(object == nil)
        return  NO;
    if ([object isMemberOfClass:[self class]])
    {
        BaseVO *other = (BaseVO *)object;
        return [other.Id isEqual:_id];
    }
    return [super isEqual: object];
}
@end
