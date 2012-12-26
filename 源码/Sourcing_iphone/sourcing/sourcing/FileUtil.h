//
//  FileUtil.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FileUtil : NSObject

+(NSString *)getAssetsFileContent:(NSString *)filename oftype:(NSString *)type;

@end
