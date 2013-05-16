//
//  ResponseVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ResponseVO.h"

@implementation ResponseVO
@synthesize code = code_;
@synthesize tag = tag_;
@synthesize msg = msg_;
@synthesize tag2 = tag2_;

-(BOOL)isSucess
{
    return self.code == CINT_CODE_SUCESS;
}

-(id)init
{
    self =[super init];
    if(self)
    {
        self.code=CINT_CODE_ERROR;
        self.msg = nil;
    }
    return self;
}

-(id) initWithResult: (NSUInteger)mcode msg:(NSString*)mmsg tag:(NSInteger)mtag
{
    self =[super init];
    if(self)
    {
        self.code = mcode;
        self.msg = mmsg;
        self.tag = mtag;
    }
    return self;
}

@end
