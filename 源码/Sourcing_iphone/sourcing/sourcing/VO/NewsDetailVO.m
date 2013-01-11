//
//  NewsDetailVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "NewsDetailVO.h"
#import "HtmlUtil.h"

@implementation NewsDetailVO
@synthesize newscontent;


#pragma mark - Superclass Overrides
-(NSString *)getHtmlContext
{
    return [HtmlUtil getHtmlContext:newscontent];
}
@end
