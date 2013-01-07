//
//  ResponseVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ResponseVO.h"

@implementation ResponseVO
@synthesize code = _code;
@synthesize tag = _tag;
@synthesize msg = _msg;
@synthesize tag2 = _tag2;

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
