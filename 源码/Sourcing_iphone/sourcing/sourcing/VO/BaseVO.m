//
//  BaseVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "HtmlUtil.h"

@implementation BaseVO

@synthesize Id = _id;

- (BOOL) isEqual:(id)object
{
    if(object == nil)
        return  NO;
    if ([object isMemberOfClass:[self class]])
    {
        BaseVO *other = (BaseVO *)object;
        return other.Id == self.Id;
    }
    return [super isEqual: object];
}

-(NSString *)getHtmlTitle
{
    return [HtmlUtil getHtmlHead];
}
-(NSString *)getHtmlSubTitle
{
    return @"";
}
-(void)setHtmlToShow:(DetailInfoCompleteBlock)kcomblock failure:(DetailInfoErrorBlock)kerrorblock
{
    _comblock = kcomblock;
    _errorblock = kerrorblock;
}
-(NSString *)generateShareText
{
    return @"";
}
-(NSString *)getHtmlContext
{
    return @"";
}
@end
