//
//  DeclareResultVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "DeclareResultVO.h"

@implementation DeclareResultVO
@synthesize declareresult = declareresult;
@synthesize declareresultcontent = declareresultcontent;
@synthesize publishtime = publishtime;
@synthesize publisher = publisher;

-(void)setDeclareresultid:(NSString *)kresultid
{
    declareresultid = kresultid;
    _id = kresultid;
}
-(NSString *)Declareresultid
{
    return declareresultid;
}

@end
