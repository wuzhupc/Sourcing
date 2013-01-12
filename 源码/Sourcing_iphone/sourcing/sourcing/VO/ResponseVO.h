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

@property  (nonatomic)NSUInteger code;   // 返回报文状态码 : 1.正常 0. 不正常
@property (nonatomic,strong)NSString *msg;     // 返回报文状态信息
@property (nonatomic)NSInteger tag; //标志位
@property (nonatomic)NSInteger tag2;

-(BOOL)isSucess;
-(id) initWithResult: (NSUInteger)mcode msg:(NSString*)mmsg tag:(NSInteger)mtag;
@end
