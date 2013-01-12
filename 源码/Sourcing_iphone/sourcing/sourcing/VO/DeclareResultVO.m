//
//  DeclareResultVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "DeclareResultVO.h"

@interface DeclareResultVO()
{
@private
    NSInteger declareresultid;
}
@end

@implementation DeclareResultVO
@synthesize declareresult = declareresult_;
@synthesize declareresultcontent = declareresultcontent_;
@synthesize publishtime = publishtime_;
@synthesize publisher = publisher_;

-(void)setDeclareresultid:(NSInteger )kresultid
{
    declareresultid = kresultid;
    self.Id = kresultid;
}
-(NSInteger )Declareresultid
{
    return declareresultid;
}

@end
