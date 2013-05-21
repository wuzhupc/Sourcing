//
//  CompanyDetailVO.m
//  sourcing
//
//  Created by wuzhu_mac on 13-5-21.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import "CompanyDetailVO.h"
#import "HtmlUtil.h"

@implementation CompanyDetailVO
@synthesize companydesc = companydesc_;
#pragma mark - Superclass Overrides
-(NSString *)getHtmlContext
{
    return [HtmlUtil getHtmlContext:self.companydesc];
}
@end
