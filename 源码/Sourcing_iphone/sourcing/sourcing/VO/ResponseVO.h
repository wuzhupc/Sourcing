//
//  ResponseVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>

#define CINT_CODE_SUCESS    0
#define CINT_CODE_ERROR     1

@interface ResponseVO : NSObject
{
    NSUInteger _code;
    NSInteger _tag;
    NSString    *_msg;
}

@property  NSUInteger code;   // 返回报文状态码 : 1.正常 0. 不正常
@property NSString *msg;     // 返回报文状态信息
@property NSInteger tag; //标志位

-(BOOL)isSucess;
@end
